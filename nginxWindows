Download : http://nginx.org/en/docs/windows.html

Start : double click on nginx.exe

#stop
C:\Users\govindarajuv\Downloads\nginx-1.17.9\nginx-1.17.9> taskkill /f /IM nginx.exe


#liststat
PS C:\Users\govindarajuv\Downloads\nginx-1.17.9\nginx-1.17.9> netstat -n -a -o | findstr "0.0.0.0:80"
  TCP    0.0.0.0:80             0.0.0.0:0              LISTENING       29012
  TCP    0.0.0.0:8020           0.0.0.0:0              LISTENING       14552
  TCP    0.0.0.0:8081           0.0.0.0:0              LISTENING       5272
  UDP    0.0.0.0:8082           *:*                                    5272


  ## nginx.conf http routing to microservice similar to zull gateway
  
  
	http {
		default_type  application/octet-stream;

		#log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
		#                  '$status $body_bytes_sent "$http_referer" '
		#                  '"$http_user_agent" "$http_x_forwarded_for"';

		#access_log  logs/access.log  main;

		sendfile        on;
		#tcp_nopush     on;

		#keepalive_timeout  0;
		keepalive_timeout  65;

		#gzip  on;

		server {
			listen       80;
			server_name  localhost;

			#charset koi8-r;

			#access_log  logs/host.access.log  main;

			location / {
				root   html;
				index  index.html index.htm;
			}

			# start sandbonx-accounts api url proxy configuration
			#
			#1. GET /accounts
			location ~^/aisp/sandbox/accounts$ {
				proxy_method GET;
				proxy_http_version 1.1;
				proxy_pass   http://localhost:8080;
			}
		}

	}
