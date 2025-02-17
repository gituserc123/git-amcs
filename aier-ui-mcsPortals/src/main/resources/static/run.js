var express = require("express");
var path = require("path");
var http =require("http");
var net = require("net");
var cp = require("child_process");
var app = express();
var defPort = 1000;


var args  = process.argv.splice(2);
console.log(args);
defPort = (/^([0-9]\d*)$/.test(args[0]))?args[0]:defPort;

var defaultEntryUrl = args[1] || 'index.html';

function portIsOccupied (port){
    const server=net.createServer().listen(port);
    return new Promise((resolve,reject)=>{
        server.on('listening',()=>{
            //console.log(`the server is runnint on port ${port}`);
            server.close()
            resolve(port)
        })

        server.on('error',(err)=>{
            //console.log(err);
            if(err.code==='EADDRINUSE'){
                resolve(portIsOccupied(port*1+1))//注意这句，如占用端口号+1
                console.log(`this port ${port} is occupied.try another.`)
            }else{
                reject(err);
            }
        })
    })

}

// 执行
portIsOccupied(defPort).then(port=>{
	// port++;
	app.all('*', function(req, res){
		var p =  path.join(__dirname, req.path);
		res.sendFile(p);
	});

	var server = app.listen(port, function () {
	  //var port = server.address().port;
	  //console.log(server.address());
	  if(defaultEntryUrl !== 'nourl'){
		cp.exec('start http://localhost:'+port+'/'+defaultEntryUrl);
	  }
	  console.log('app runing at http://localhost:',port);
	});
});

