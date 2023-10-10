
const baseUrl = "/NmBook";

const urlMap = {
	login: baseUrl + "/login",
	logout: baseUrl + "/logout",
	loginCheck: baseUrl +"/getUserInfo",//获取账号信息
	getUserData: baseUrl +"/getUserData",
	uploadFile: baseUrl +"/uploadFile",
	regist: baseUrl+"/regist",

	findNmBookList: baseUrl+ "/nmBook/queryList",
	findNmBook: baseUrl+ "/nmBook/queryObject",
	addNmBook: baseUrl+ "/nmBook/addNmBook",
	editNmBook: baseUrl+ "/nmBook/editNmBook",

	findNmBookGroupList: baseUrl+ "/nmBookGroup/queryList",
	findNmBookGroup: baseUrl+ "/nmBookGroup/queryObject",
	addNmBookGroup: baseUrl+ "/nmBookGroup/addNmBookGroup",
	editNmBookGroup: baseUrl+ "/nmBookGroup/editNmBookGroup",

	findNmBookEvaluateList: baseUrl+ "/nmBookEvaluate/queryList",
	findNmBookEvaluate: baseUrl+ "/nmBookEvaluate/queryObject",
	addNmBookEvaluate: baseUrl+ "/nmBookEvaluate/addNmBookEvaluate",
	editNmBookEvaluate: baseUrl+ "/nmBookEvaluate/editNmBookEvaluate",

	findNmBookChapterList: baseUrl+ "/nmBookChapter/queryList",
	findNmBookChapter: baseUrl+ "/nmBookChapter/queryObject",
	addNmBookChapter: baseUrl+ "/nmBookChapter/addNmBookChapter",
	editNmBookChapter: baseUrl+ "/nmBookChapter/editNmBookChapter",

	findNmBookCollectList: baseUrl+ "/nmBookCollect/queryList",
	findNmBookCollect: baseUrl+ "/nmBookCollect/queryObject",
	addNmBookCollect: baseUrl+ "/nmBookCollect/addNmBookCollect",
	editNmBookCollect: baseUrl+ "/nmBookCollect/editNmBookCollect",

	findSysUserList: baseUrl+ "/sysUser/queryList",
	findSysUser: baseUrl+ "/sysUser/queryObject",
	addSysUser: baseUrl+ "/sysUser/addSysUser",
	editSysUser: baseUrl+ "/sysUser/editSysUser",

	getPic : baseUrl + "/getFile/0/",

	getAudio : baseUrl + "/getFile/1/",

	localPic: baseUrl + "/static/img/"
}

const getUserInfo = function(){
	let user = localStorage.getItem("userInfo");
	if(user){
		return JSON.parse(user);
	}else{
		err("登录过期，请重新登录");
		localStorage.removeItem("userInfo");
	}
}

const request = function(requestOpt) {
	let loading;
	try {
		requestOpt = requestOpt || {};
		if (!requestOpt.url) {
			return;
		}
		var token = "";
		try {
			let value = localStorage.getItem("userInfo");
			if (value) {
				value = JSON.parse(value);
				token = value.token;
			}
		} catch (e) {
			token = "";
		}
		requestOpt.data = requestOpt.data || {};
		requestOpt.data['token'] = token;
		requestOpt.method = requestOpt.method || 'post';
		requestOpt.headers = requestOpt.headers || {
			//'content-type': 'application/x-www-form-urlencoded',
			'token': token
		};
		requestOpt.timeout = requestOpt.timeout || 30000;
		requestOpt.responseType = requestOpt.responseType || 'text';
		requestOpt.dataType = requestOpt.dataType || "json";
		requestOpt.sslVerify = requestOpt.sslVerify || true;
		requestOpt.success = requestOpt.success || function(res) {};
		requestOpt.fail = requestOpt.fail || function(res) {};
		requestOpt.complete = requestOpt.complete || function(res) {};

		loading = ELEMENT.Loading.service({
			lock: true,
			text: 'Loading',
			spinner: 'el-icon-loading'
		});
		axios({
			method: requestOpt.method,
			url: requestOpt.url,
			headers:requestOpt.headers,
			data: requestOpt.data
		}).then(function (response) {
			loading.close();
			requestOpt.success(response);
			if(response.data.code == 401){
				err("未登录")
				localStorage.removeItem('user');
			}
		}).catch(function (error) {
			loading.close();
			requestOpt.fail(error);
		});
	} catch (e) {
		//TODO handle the exception
		loading.close();
		err("网络请求异常")
	}
}


const getUrlParam = function(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

const msg = function(msg){
	ELEMENT.Message({showClose: true,message: msg,offset:'300',center:true});
}

const err = function(msg){
	ELEMENT.Message({message:msg,type:"error",offset:'300',center:true});
}

const warn = function(msg){
	ELEMENT.Message({message:msg,type:"warning",offset:'300',center:true});
}

const success = function(msg){
	ELEMENT.Message({message:msg,type:"success",offset:'300',center:true});
}

export default {
	urlMap,
	getUserInfo,
	request,
	msg,
	err,
	warn,
	success,
	getUrlParam
}
