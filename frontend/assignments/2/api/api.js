const express = require('express');
const app = express();
app.use(express.json());


app.get('/api/posts', (req, res) => {
    const { pageNumber, pageSize } = req.query;
    res.json({ pageNumber, pageSize });
});

app.post('/api/posts', (req, res) => {
    const { id, title, content } = req.body;
    res.json({ id, title, content });
});

app.post('/api/user/login', (req, res) => {
    const { email, password } = req.body;
    res.json({ email, password });
});

app.get('/api/post/:id', (req, res) => {
    const postId = req.params.id;
    res.json({ postId });
});


const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
