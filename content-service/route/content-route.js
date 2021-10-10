const express = require("express");
const router = express.Router();
const contentController = require('../controller/content-controller');

router.get('/:id', contentController.readContent);
router.post('/', contentController.createContent);
router.delete('/:id', contentController.deleteContent);

module.exports = router;