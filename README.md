TelcosRest
==========

REST API for TeleSparkWeb

accepts file pushed into the service. Checks that the file is a zip file.if true unpacks zip files.expects zip file to contain
a single comma separated values file. unpacks the csv file and transform the data to a json object and sends that object to
Rabbit MQ.
