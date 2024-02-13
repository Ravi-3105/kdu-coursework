const tweet = require("../data/x");
const express = require("express");
const { v4: uuidv4 } = require('uuid'); // Import uuidv4 function from the uuid package

const route = express.Router();

route.get("/get/:id", (req, res) => {
  const search_id = req.params.id;
  const post = tweet.filter((x) => x.id === search_id);

  if (post.length === 0) {
    res.status(404).json({
      message: "Tweet not found",
    });
  }
  res.json(post[0]);
});

route.get("/get", (req, res) => {
  res.json({
    data: tweet,
  });
});

route.post("/post", (req, res) => {
  console.log(req.body.post);
  const post = {
    id: uuidv4(), // Generate UUID for the ID
    post: req.body.post,
  };
  tweet.push(post);
  res.status(201).json(post);
});

module.exports = route;
