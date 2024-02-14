
  function post(event) {
    // Prevent the default form submission behavior
    event.preventDefault();

    // Get the value of the input field
    var tweetContent = document.querySelector('.tweet-box input').value;

    // Check if the input field is not empty
    if (tweetContent.trim() !== '') {
      // Create a new tweet element
      var newTweet = document.createElement('div');
      newTweet.classList.add('twitter');

      // Construct the HTML structure of the tweet
      newTweet.innerHTML = `
        <div class="user-img"><img src="img/nit-icon.png" alt="profile-img"></div>
        <div class="user-content-box">
          <div class="user-names">
            <p class="full-name">Nitesh Gupta</p>
            <p class="user-name">@nit_hck</p>
            <p class="time">. 34 sec</p>
          </div>
          <div class="user-content">
            <p>${tweetContent}</p>
          </div>
          <div class="content-icons">
            <a href="#" class="icon-link">
              <div class="icon-container">
                <img src="img/Comment-icon.png" alt="Comment" class="icon-image"> 0
              </div>
            </a>
            <a href="#" class="icon-link">
              <div class="icon-container">
                <img src="img/reshare-icon.png" alt="Retweet" class="icon-image">0
              </div>
            </a>
            <a href="#" class="icon-link">
              <div class="icon-container">
                <img src="img/like-icon.png" alt="Heart" class="icon-image">0
              </div>
            </a>
            <a href="#" class="icon-link">
              <div class="icon-container">
                <img src="img/stats.png" alt="Upvote" class="icon-image">0
              </div>
            </a>
            <a href="#" class="icon-link">
              <div class="icon-container">
                <img src="img/second-bookmark.png" alt="Upvote" class="icon-image">
                <img src="img/share.png" alt="Upvote" class="icon-image">
              </div>
            </a>
          </div>
        </div>
      `;

      // Append the new tweet to the twitter container
      document.querySelector('.twitter-container').appendChild(newTweet);

      // Reset the input field
      document.querySelector('.tweet-box input').value = '';
    } else {
      // If the input field is empty, alert the user
      alert('Please enter something to post.');
    }
  }

