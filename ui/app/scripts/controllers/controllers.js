/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.controllers', ['twitterstream.services', 'nvd3'])
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
		
		$scope.options = {
			chart: {
				type: 'discreteBarChart',
				height: 450,
				width: 800,
				margin : {
					top: 20,
					right: 20,
					bottom: 60,
					left: 65
				},
				x: function(d){ return d.label; },
				y: function(d){ return d.value; },
				showValues: true,
				valueFormat: function(d){
					return d3.format(',.f')(d);
				},
				transitionDuration: 500,
				xAxis: {
					axisLabel: 'Tweet Keyword'
				},
				yAxis: {
					axisLabel: 'Count Per Keyword',
					axisLabelDistance: 20
				}
			}
		};
		
		console.log(tweetStatsService.getData().then(
				function(response) {
					$scope.data = response.data
				}, 
				function(response) {
					console.log("Error: " + response.statusText)
				}
			)
		);
	}]);