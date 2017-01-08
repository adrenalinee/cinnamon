var gulp = require('gulp');
var concat = require('gulp-concat');
var webpack = require('webpack-stream');

gulp.task('scripts', function() {
	gulp.src('assets/js/index.js')
	.pipe(webpack({
		module:{
			loaders:[
				{test: /\.css$/, loader: 'style!css!'}
			]
		}
	}))
	.pipe(concat('app.index.bundle.js'))
	.pipe(gulp.dest('target/classes/static/js'));
	
	gulp.src('assets/js/initWizard.js')
	.pipe(webpack({
		module:{
			loaders:[
				{test: /\.css$/, loader: 'style!css!'}
			]
		}
	}))
	.pipe(concat('app.innitWizard.bundle.js'))
	.pipe(gulp.dest('target/classes/static/js'));
});