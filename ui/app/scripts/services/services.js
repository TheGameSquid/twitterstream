/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.services', [])
	.factory('tweetStreamService', function() {
		var tweetFeed = new EventSource("/feed");
				
		return {
       		getTweets: function(callback) {
         		tweetFeed.addEventListener("message", callback, false);
			}
		}; 
	});