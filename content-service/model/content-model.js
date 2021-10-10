const mongoose = require('mongoose');

const contentSchema = mongoose.Schema({
    content: {
        type: String,
        required: true,
    },
    contentType: {
        type: String,
        required: true,
    },
    createdBy: {
        type: String,
        required: true,
    },
    created: {
        type: Date,
        default: new Date(),
    }
})
var content = mongoose.model('content', contentSchema);

module.exports = content;