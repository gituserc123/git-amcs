var path = require("path");
var gulp = require('gulp');
var md5 = require('gulp-md5-soplus');
var minimist = require('minimist');

var requirejs = require('gulp-requirejs-simple');

var mainMd5StrOpt = {
  string: 'str',
  default: { str: '' }
};
var options = minimist(process.argv.slice(2), mainMd5StrOpt);


var bPath = './resources/static/';
var paths = {
  css: [bPath + 'css/*.css', '!' + bPath + 'css/*.min.css'], //需要监控变化的css文件
  jss: [bPath + 'js/**/*.js', '!' + bPath + 'js/out/**/*.js'], //需要监控变化的js文件
  js: bPath + 'js/'
};

var minJsTask = {
  main: { //压缩bmap main.js
    baseUrl: paths.js,
    name: 'main.un',
    out: paths.js + 'main.js',
    optimize: 'uglify2',
    uglify: {},
    uglify2: {
      output: {
        screw_ie8: false,
        beautify: false
      },
      compress: {
        screw_ie8: false,
        sequences: false,
        global_defs: {
          DEBUG: false
        }
      },
      warnings: false,
      mangle: false
    },
    mainConfigFile: paths.js + 'config.require.js',
    watchFile: [paths.js + '*.js'] //需要监控变化的js文件
  }
}


//var jsTaskArr = [];
//for (task in minJsTask) {
//    gulp.task(task, requirejs(minJsTask[task]));
//    jsTaskArr.push(task);
//};
//gulp.task('minjs', jsTaskArr);

gulp.task('minjs', (cb) => {
  requirejs(minJsTask.main)(cb);
});

gulp.task('MD5', function (cb) {
  gulp.src([
		"./resources/static/js/**/*.js",
		"./resources/static/js/**/*.css",
		// "./resources/static/js/plus/**/*.js",
		// "./resources/static/js/app/**/*.js",
		"./resources/static/js/app/**/*.html",
		"./resources/static/css/**/*.css"
	]).pipe(md5(5, [
		'./resources/static/css/socss.css',
		'./resources/static/js/main.un.js',
		'./resources/static/js/requirePaths.js',
		'./webapp/WEB-INF/views/common/include_js.ftl',
		'./webapp/WEB-INF/views/common/include_resources.ftl',
		'./webapp/WEB-INF/views/sys/login.ftl',
		'./webapp/WEB-INF/views/sys/index.ftl'
    ], {
		mappingFile: 'manifest.json'
    })); // ,'./resources/static/js/config.require.js'
  // .pipe(gulp.dest("./resources/static/js"));
  cb();
});

//console.log(options.str);
gulp.task('markMain', function (cb) {
  gulp.src([
		"./resources/static/js/main.js",
	]).pipe(md5(5, [
		'./webapp/WEB-INF/views/common/include_js.ftl',
		'./webapp/WEB-INF/views/common/include_resources.ftl',
		'./webapp/WEB-INF/views/sys/login.ftl',
		'./webapp/WEB-INF/views/sys/index.ftl'
    ], {
		specVerStrFiles: options.str?['main.js']:[],//有 str 才加文件入列表打时间版本号，否则仍然执行md5版本号
		specVerStr: options.str?options.str:'',
		mappingFile: 'manifest.json'
    }));
  cb();
});

gulp.task('default', gulp.series('MD5','minjs',function (cb) {
  console.log(process.cwd(), ' = finished = ^__^~~~~');
  cb();
}));