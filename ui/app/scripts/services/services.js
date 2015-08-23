/// </// <reference path="angularjs/angular.d.ts" />

'user strict'

angular.module('twitterstream.services', [])
	.factory('tweetService', function() {
		var tweets = [];
		var tweetFeed = new EventSource("/feed");
        tweetFeed.addEventListener("message", feedCallback, false);
		
		return {
        	getTweets: getTweets
    	};

		function getTweets() {
			return tweets;
		}		
		
		function feedCallback(message) {
			var tweet = JSON.parse(message.data);
			tweets.push(tweet);
		}
	})