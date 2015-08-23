/// </// <reference path="angularjs/angular.d.ts" />

'user strict'

angular.module('twitterstream.services', [])
	.factory('tweetService', function() {
		var tweets = [];
		
		return {
        	getTweets: getTweets
    	};

		function getTweets() {
			return tweets;
		}
		
		var tweetFeed = new EventSource("/feed");
        tweetFeed.addEventListener("message", feedCallback, false);
		
		function feedCallback(message) {
			var tweet = JSON.parse(message.data);
			console.log("Received callback")
			tweets.push(tweet);
		}
	})