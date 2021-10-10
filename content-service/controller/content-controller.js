const content = require('../model/content-model');

const createContent = async (req, res) => {

  if (req.body && req.body.content && req.body.contentType && req.body.createdBy) {

    const contentToSave = new content({
      content: req.body.name,
      contentType: req.body.roll,
      createdBy: req.body.registration
    })

    try {
      await contentToSave.save();

      res.status(201).json(contentToSave);

    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  } else {
    res.status(400).json({ message: "Invalid request" });
  }
}

const readContent = async (req, res) => {

  if (!req.params.id) {
    res.status(400).json({ message: "Invalid request" });
  }

  const id = req.params.id;

  try {
    const file = await content.findOne({ "_id": id });

    res.status(200).json(file);
  } catch (error) {
    res.status(404).json({ message: error.message });
  }
}

const deleteContent = async (req, res) => {

  if (!req.params.id) {
    res.status(400).json({ message: "Invalid request" });
  }

  const id = req.params.id;

  try {
    await content.findOneAndRemove({ "_id": id });
    res.status(203).json({ id });

  } catch (error) {
    res.status(402).json({ message: error.message });
  }
}

module.exports = {
  createContent,
  readContent,
  deleteContent
};