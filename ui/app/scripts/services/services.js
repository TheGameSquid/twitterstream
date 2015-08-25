/// <reference path="../../../typings/angularjs/angular.d.ts" />

'user strict'

angular.module('twitterstream.services', [])
	.factory('tweetService', function() {
		var tweetFeed = new EventSource("/feed");
				
		return {
       		getTweets: function(callback) {
         		tweetFeed.addEventListener("message", callback, false);
			}
		}; 
	})