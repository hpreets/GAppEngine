set wsdluri=./war/WorkflowContactOutboundMessage.wsdl
set gensrcdir=./src
set targetpackage=com.sforce.soap.outboundmessage
set genoutdir=./war/WEB-INF/classes
wsimport -d "%genoutdir%" -s "%gensrcdir%" -p %targetpackage% -keep "%wsdluri%"
