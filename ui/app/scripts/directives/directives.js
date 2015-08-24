/// <reference path="../../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream.directives', [])
    // The Twitter cards
    .directive('tweetCard', function () {
        return {
            restrict: 'C',
            scope: { tweet: "=tweet" },
            templateUrl: "views/tweetcard.html",
            link: function (scope, elem, attrs) { }
        }
    });