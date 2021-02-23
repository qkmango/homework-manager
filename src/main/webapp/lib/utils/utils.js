function getFileType(filePath) {
    let index = filePath.lastIndexOf(".");
    let ext = filePath.substr(index+1);
    return ext;
}