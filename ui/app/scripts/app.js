/// <reference path="../../typings/angularjs/angular.d.ts" />

'use strict';

angular.module('twitterstream', 
	[
		'twitterstream.controllers', 
		'twitterstream.directives', 
		'twitterstream.services', 
		'twitterstream.animations', 
		'angularMoment' 
	])
	
	.constant('angularMomentConfig', {
    	preprocess: 'unix',
    	timezone: 'Europe/London'
	});