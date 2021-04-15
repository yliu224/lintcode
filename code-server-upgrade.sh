upgrade_code_server(){
        curl -fsSL https://code-server.dev/install.sh | sh
        cat /usr/lib/code-server/lib/vscode/product.json | sudo jq '. + {"extensionsGallery": {"serviceUrl": "https://marketplace.visualstudio.com/_apis/public/gallery","cacheUrl": "https://vscode.blob.core.windows.net/gallery/index","itemUrl": "https://marketplace.visualstudio.com/items"}}' | sudo tee /usr/lib/code-server/lib/vscode/product.json
        sudo systemctl start code-server
}