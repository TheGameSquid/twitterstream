/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.directives', [])
    // The Twitter cards
    .directive('tweetCard', function () {
        return {
            restrict: 'C',
            require: 'tweet',
            scope: { tweet: "=tweet" },
            templateUrl: "views/tweetcard.html",
            link: function (scope, elem, attrs) { }
        }
    })
    
    // Ensures data-tweet attribute can be enforced for directive above 
    .directive('tweet', function() {
        return {
            controller: function($scope) {}
        }
    })
    
    // A bar-chart using D3
    .directive('barsVisualization', function() {
        return {
            restrict: 'C',
            require: 'stats',
            scope: {
              stats: '=stats'  
            },
            templateUrl: 'views/barsvisualization.html',
            link: function(scope, elem, attrs) {
                // Get D3 details
            }
        }
    })
    
    // Ensures data-tweet attribute can be enforced for directive above 
    .directive('stats', function() {
        return {
            controller: function($scope) {}
        }
    });