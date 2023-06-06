// server.js
const express = require('express');
const axios = require('axios');
const app = express();
const msal = require('@azure/msal-node');
const port = process.env.PORT || 3000;
const SpringCloudGatewayURL = "<Spring cloud Gateway URL>"
app.use(express.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
app.set('view engine', 'ejs');

// MSAL Config
const msalConfig = {
    auth: {
        clientId: "< SPA App Registration ClientId>",
        authority: "https://login.microsoftonline.com/< TenantId >/",
        clientSecret: "<SPA App Registration ClientSecret>",

    },
};

const cca = new msal.ConfidentialClientApplication(msalConfig);

const tokenRequest = {
    scopes: ["api://<Spring Cloud Gateway app registration ClientID>/.default"]
};
// Function to get token
async function getToken() {
    const response = await cca.acquireTokenByClientCredential(tokenRequest);
    console.log(response.accessToken);
    return response.accessToken;
}

app.get('/', (req, res) => {
    res.render('index');
});

app.post('/addBook', async (req, res) => {
    const author = req.body.author;
    const title = req.body.title;
    const data = {
        author: author,
        title: title,
    };
    const url = SpringCloudGatewayURL + '/books/add';
    try {
        const token = await getToken();
        const apiResponse = await axios.post(url, data, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (apiResponse.status !== 200) {
            res.send(`Error occurred while adding book: HTTP status code ${apiResponse.status}`);
        } else {
            res.send('Book added successfully: ' + JSON.stringify(apiResponse.data));
        }

    } catch (error) {
        res.send('Error occurred while adding book: ' + error);
    }
});

app.get('/getBook', async (req, res) => {
    const id = req.query.id;
    const url = SpringCloudGatewayURL + `/books/${id}`;

    try {
        const token = await getToken();
        const apiResponse = await axios.get(url, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        if (apiResponse.status !== 200) {
            res.send(`Error occurred while adding book: HTTP status code ${apiResponse.status}`);
        } else {
            res.send('Book details: ' + JSON.stringify(apiResponse.data));
        }
    } catch (error) {
        res.send('Error occurred while fetching book details: ' + error);
    }
});


app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
