var ar1 = [
    'busRoute',
    'busStation',
    'busBuilding'
];
var ar2 = [
    'BusRoute',
    'BusStation',
    'BusBuilding'
]
var text = "";
for(var i =0;i<ar1.length;i++){

    var obj = ar1[i];
    var obj2 = ar2[i];

    text += "find"+obj2+'List: baseUrl+ "/'+obj+'/queryList",';
    text += "find"+obj2+': baseUrl+ "/'+obj+'/queryObject",';
    text += "add"+obj2+': baseUrl+ "/'+obj+'/add'+obj2+'",';
    text += "edit"+obj2+': baseUrl+ "/'+obj+'/edit'+obj2+'",';


}
console.log(text);