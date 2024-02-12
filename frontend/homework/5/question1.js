const http = require('http');
const os = require('os');
const fs = require('fs');

function generateSystemInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        CPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd()
    };
}

function saveSystemInfoToFile() {
    const systemInfo = generateSystemInfo();
    const jsonContent = JSON.stringify(systemInfo, null, 2);
    fs.writeFileSync('system_info.json', jsonContent);
}

saveSystemInfoToFile();

const server = http.createServer((req, res) => {
    fs.readFile('system_info.json', (err, data) => {
        if (err) {
            res.writeHead(500, {'Content-Type': 'text/plain'});
            res.end('Internal Server Error');
            return;
        }

        res.writeHead(200, {'Content-Type': 'application/json'});
        res.write(`Hello, my name is Ravi!\n\n`);
        res.write(`Here is my system information:\n\n`);
        res.end(data);
    });
});

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
