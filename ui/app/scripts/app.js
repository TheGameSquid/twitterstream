'use strict';

angular.module('twitterstream', ['twitterstream.controllers', 'twitterstream.directives', 'twitterstream.services', 'angularMoment'])
	.constant('angularMomentConfig', {
    	preprocess: 'unix',
    	timezone: 'Europe/London'
	});