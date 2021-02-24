function getFileType(filePath) {
    let index = filePath.lastIndexOf(".");
    let ext = filePath.substr(index+1);
    return ext;
}

function getUrlParam(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]);
    return null;
}