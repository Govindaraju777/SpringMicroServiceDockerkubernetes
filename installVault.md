# Vault installation in windows 10

### 1) Download https://releases.hashicorp.com/vault/1.2.3/vault_1.2.3_windows_amd64.zip
### 2) Setup Environment Varialbes
      VAULT_ADDR = 127.0.0.1
      VAULT_DEV_ROOT_TOKEN_ID = mytoken
      path=D:\Govindaraju\programs\vault_1.2.3_windows_amd64

#### 3) open power shell and start vault in deve mode
      vault server -dev
### 4) Access vault on port 8200
    http://localhost:8200
    
    access token : mytoken
    
    
 ### background process
  https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/start-process?view=powershell-6
  
  D:\Govindaraju\programs\vault_1.2.3_windows_amd64> Start-Process -FilePath "vault.exe" -ArgumentList "server -dev" -Wait -WindowStyle Minimized
  
    
    
    
  
