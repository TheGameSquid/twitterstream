/// </// <reference path="angularjs/angular.d.ts" />

'user strict';

angular.module('twitterstream.controllers', ['twitterstream.services'])
	.controller('tweetController', ['$scope', 'tweetService', function($scope, tweetService) {
		$scope.tweets = [];
		
		tweetService.getTweets(function(response) {
        	var tweet = JSON.parse(response.data);
			if ($scope.tweets.length == 100) {
				$scope.tweets.pop();
			}
          	$scope.tweets.unshift(tweet);
			$scope.$apply();
      	});
	}]);