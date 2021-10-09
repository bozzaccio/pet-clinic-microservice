require('dotenv').config()

const express = require('express');
const app = express();
const port = 3000;
const initRoutes = require("./route/router");
const mongoose = require("mongoose");

mongoose.connect(process.env.MONGO_URI + '/' + process.env.MONGO_DB);

app.use(express.urlencoded({ extended: true }));
initRoutes(app);

app.listen(port, () => {
    console.log(`Running at.... http://localhost:${port}`)
});
