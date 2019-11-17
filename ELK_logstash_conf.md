
  1) Elastic search https://www.elastic.co/downloads/elasticsearch
    - Unzip 
    - cd C:\Users\govindarajuv\Downloads\ELK\elasticsearch-7.4.2\bin
    - .\elasticsearch.bat
    - http://localhost:9200/
	
  2) Kibana https://www.elastic.co/downloads/kibana
    - unzip
    - cd C:\Users\govindarajuv\Downloads\ELK\kibana-7.4.0-windows-x86_64\bin
    - kibana.bat
    - http://localhost:5601/

  3) Logstash https://www.elastic.co/downloads/logstash
    - unzip
    - cd /bin
    - create logstash.conf under bin direcotory.
      - Here configure 
        log file path , filter grok pattern , outputcodec, elastic search host:port
	
  
  
  #### /bin/logstash.conf
  
	    input {
	    file {
	      type => "java"
	      path => [
	      "D:/elk/*.log",
	      "D:/elk/*.json",
	      "C:/Users/govindarajuv/demo/*.log",
	      "C:/Users/govindarajuv/demo/*.json",
	      "C:/Users/govindarajuv/t24-connector/*.log",
	      "C:/Users/govindarajuv/t24-connector/*.json"
	    ]
	      codec => multiline {
		pattern => "^%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME}.*"
		negate => "true"
		what => "previous"
	      }
	    }
	  }
	  filter {
		 # pattern matching logback pattern
		 grok {
			match => { "message" => "%{TIMESTAMP_ISO8601:timestamp}\s+%{LOGLEVEL:severity}\s+\[%{DATA:service},%{DATA:trace},%{DATA:span},%{DATA:exportable}\]\s+%{DATA:pid}\s+---\s+\[%{DATA:thread}\]\s+%{DATA:class}\s+:\s+%{GREEDYDATA:rest}" }
		 }
	  }
	  output {
	    stdout {
	      codec => rubydebug
	    }
	    # Sending properly parsed log events to elasticsearch
	    elasticsearch {
	      hosts => ["localhost:9200"]
	    }
	  }


### start logstash
  C:\Users\govindarajuv\Downloads\ELK\logstash-7.4.2\logstash-7.4.2\bin> .\logstash -f .\logstash.conf
  


   ### spring boot logback configuration
   ### https://cloud.spring.io/spring-cloud-sleuth/reference/html/

   <?xml version="1.0" encoding="UTF-8"?>
  <!--
    ~ Copyright (C) 2019 Maveric Systems. - All Rights Reserved
    ~
    ~ Unauthorized copying or redistribution of this file in source and binary forms via any medium
    ~ is strictly prohibited.
    -->

  <configuration>
      <springProfile name="log_http_requests">
          <logger name="org.springframework.web.filter.CommonsRequestLoggingFilter">
              <level value="DEBUG"/>
          </logger>
      </springProfile>

      <include resource="org/springframework/boot/logging/logback/defaults.xml"/>
      ​
      <springProperty scope="context" name="springAppName" source="spring.application.name"/>
      <!-- Example for logging into the build folder of your project -->
      <property name="LOG_FILE" value="${user.home}/${springAppName}"/>​

      <!-- You can override this to have a custom pattern --> 
      <property name="CONSOLE_LOG_PATTERN"
                value='%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta}
      %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %replace(
      %replace( %replace(
      %replace(%msg){"&lt;password&gt;.+&lt;\/password&gt;",
      "&lt;password&gt;*****&lt;\/password"}){"&lt;fieldName&gt;\\s*ACCTID\\s*&lt;\/fieldName&gt;\\s*&lt;value&gt;\\s*.*\\s*&lt;\/value&gt;",
      "&lt;fieldName&gt;ACCTID&lt;\/fieldName&gt;&lt;value&gt;*****&lt;\/value&gt;"}
      ){"(authorization(.)*Basic)(\s+)(\w*)", "Authorization Basic
      *****"}){"(authorization(.)*Bearer)(\s+)(\w*)", "Authorization Bearer *****"}%n'/>

      <!-- Appender to log to console -->
      <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
          <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
              <!-- Minimum logging level to be presented in the console logs-->
              <level>DEBUG</level>
          </filter>
          <encoder>
              <pattern>${CONSOLE_LOG_PATTERN}</pattern>
              <charset>utf8</charset>
          </encoder>
      </appender>

      <!-- Appender to log to file -->​
      <appender name="flatfile" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>${LOG_FILE}/${springAppName}.log</file>
          <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
              <fileNamePattern>${LOG_FILE}/${springAppName}-%d{yyyy-MM-dd}-%i.gz</fileNamePattern>
              <maxHistory>30</maxHistory>
              <maxFileSize>100MB</maxFileSize>
          </rollingPolicy>
          <encoder>
              <pattern>${CONSOLE_LOG_PATTERN}</pattern>
              <charset>utf8</charset>
          </encoder>
      </appender>

      <!-- Appender to log to file in a JSON format -->
      <appender name="logstash" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>${LOG_FILE}/${springAppName}.json</file>
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>${LOG_FILE}.json.%d{yyyy-MM-dd}.gz</fileNamePattern>
              <maxHistory>7</maxHistory>
          </rollingPolicy>
          <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
              <providers>
                  <timestamp>
                      <timeZone>UTC</timeZone>
                  </timestamp>
                  <pattern>
                      <pattern>
                          {
                          "severity": "%level",
                          "service": "${springAppName:-}",
                          "trace": "%X{X-B3-TraceId:-}",
                          "span": "%X{X-B3-SpanId:-}",
                          "parent": "%X{X-B3-ParentSpanId:-}",
                          "exportable": "%X{X-Span-Export:-}",
                          "pid": "${PID:-}",
                          "thread": "%thread",
                          "class": "%logger{40}",
                          "rest": "%message"
                          }
                      </pattern>
                  </pattern>
              </providers>
          </encoder>
      </appender>
      ​
      ​
      <!-- console  -->
      <springProfile name="log_CONSOLE_INFO">
          <root level="INFO">
              <appender-ref ref="console"/>
          </root>
      </springProfile>
      <springProfile name="log_CONSOLE_DEBUG">
          <root level="DEBUG">
              <appender-ref ref="console"/>
          </root>
      </springProfile>

      <!-- flatfile -->
      <springProfile name="log_FLAT_FILE_INFO">
          <root level="INFO">
              <appender-ref ref="flatfile"/>
              <appender-ref ref="logstash"/>
          </root>
      </springProfile>
      <springProfile name="log_FLAT_FILE_DEBUG">
          <root level="DEBUG">
              <appender-ref ref="flatfile"/>
              <appender-ref ref="logstash"/>
          </root>
      </springProfile>

  </configuration>
 
 
 

