/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.services', [])
	// Provides callback to the steam of SSEs
	.factory('tweetStreamService', function() {
		var tweetFeed = new EventSource("/api/feed");
				
		return {
       		getTweets: function(callback) {
         		tweetFeed.addEventListener("message", callback, false);
			}
		}; 
	})
	
	// Fetches analytics data from the backend
	.factory('tweetStatsService', [ '$http', function($http) {		
		return {
			getData: getData	
		}
		
		function getData() {
			return $http.get('/api/sample');
		}
	}]);