require('dotenv').config()

const express = require('express');
const mongoose = require('mongoose');
const contentRouter = require("./route/content-route");

const app = express();

mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true });
const con = mongoose.connection;
app.use(express.json());
try {
    con.on('open', () => {
        console.log('connected');
    })
} catch (error) {
    console.log("Error: " + error);
}

app.use('/ccontent', contentRouter);


app.listen(process.env.SERVER_PORT, () => {
    console.log(`Running at.... http://${process.env.SERVER_URL}:${process.env.SERVER_PORT}`)
});