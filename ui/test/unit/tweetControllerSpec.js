/// <reference path="../../typings/jasmine/jasmine.d.ts" />

'use strict';

describe('tweetController', function() {
	var $scope;
	
	beforeEach(function() {
		module('twitterstream');

		inject(function($rootScope, $controller, $injector) {
            $scope = $rootScope.$new();
			
			// Fake tweet to be used in test
			$scope.newTweet = {
				'id' : '1000000',
				'timeCreated' : 'Just now ;)',
				'timeCreatedMs' : '1400000',
				'text': 'Some of these tweets are really lame',
				'userName' : 'TwitterStream',
				'screenName' : 'therealtwitterstream',
				'imageUrl': 'https://twitter.com/8374834738/somepic.png'	
			};
			
            $controller('tweetController', {
                $scope: $scope
            });
        });
	});
	
	describe('addTweet', function() {
		it('should add precisely one tweet to the list', function() {
            // GIVEN
            var newTweet = $scope.newTweet;

            // WHEN
            $scope.addTweet(newTweet);
			
			// THEN
			expect($scope.tweets.length).toBe(1);
			expect($scope.tweets[0]).toBe(newTweet);
        });
		
		it('should never exceeds more than 100 tweets', function() {
			// GIVEN
            var newTweet = $scope.newTweet;

            // WHEN        
			var tweetsToAdd = 100;
			while(tweetsToAdd--) {
				$scope.addTweet(newTweet);
			}
			$scope.addTweet(newTweet);
			
			// THEN
			expect($scope.tweets.length).toBe(100);
		});
	});
});