var gulp = require('gulp');
var concat = require('gulp-concat');

gulp.task('scripts', function() {
	gulp.src('assets/js/**')
	.pipe(concat('cinnamon.core.bundle.js'))
	.pipe(gulp.dest('target/classes/static/js'))
	.pipe(gulp.dest('dist/'));
	
	gulp.src('assets/css/**')
	.pipe(concat('cinnamon.core.css'))
	.pipe(gulp.dest('target/classes/static/css'))
	.pipe(gulp.dest('dist/'));
});