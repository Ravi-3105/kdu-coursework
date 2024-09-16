const path = require('path');

function getFileInformation(filePath) {
    return {
        extension: path.extname(filePath),
        baseName: path.basename(filePath),
        directory: path.dirname(filePath)
    };
}

function processFilePaths(filePaths) {
    return filePaths.map(filePath => getFileInformation(filePath));
}

const filePaths = [

    'dir1/dir2/file1.txt',
  
    'dir1/dir3/file2.txt',
  
    'dir1/dir3/file3.md',
  
    'dir4/file4.jpg',
  
    'dir4/file5.pdf',
  
  ];

console.log(processFilePaths(filePaths));
