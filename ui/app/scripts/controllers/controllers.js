/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.controllers', ['twitterstream.services'])
	.controller('tweetController', ['$scope', 'tweetStreamService', function($scope, tweetStreamService) {
		$scope.tweets = [];
		
		$scope.addTweet = function(tweet) {
			if ($scope.tweets.length == 100) {
				$scope.tweets.pop();
			}
          	$scope.tweets.unshift(tweet);
			$scope.$apply();
		};
		
		tweetStreamService.getTweets(function(response) {
        	var tweet = JSON.parse(response.data);
			$scope.addTweet(tweet);
      	});
	}])
	
	.controller('tweetStatsController', ['$scope', 'tweetStreamService', 'tweetStatsService', function($scope, tweetStreamService, tweetStatsService) {
		$scope.stats = { stats: [ { hashtag: "angular", count: 1000 } ] };
		
		$scope.addTweet = function(tweet) {
			//$scope.stats = tweetStatsService.getData();
			//$scope.stats = { stats: [ { hashtag: "angular", count: 1000 } ] }
			//$scope.$apply();
		};
	}]);