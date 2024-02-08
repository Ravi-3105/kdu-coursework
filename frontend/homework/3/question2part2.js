function codeString(str) {

    str = str.trim();

    str = str.replace(/a/g, '4');
    str = str.replace(/e/g, '3');
    str = str.replace(/i/g, '1');
    str = str.replace(/o/g, '0');
    str = str.replace(/s/g, '5');
    return str;
}

console.log(codeString("javascript is cool  ")); 
console.log(codeString("programming is fun")); 
console.log(codeString("  become a coder")); 
