This repository contains a mode choice estimation tool that integrates pathfinding into the estimation procedure.


# Routing package (src/main/java/routing)
This package contains various methods for assessing travel time and disutility for active modes incorporating the street-level environment.
The walk and cycle routing algorithms are based largely on code from the "bicycle" MATSim extension which
can be found here: https://github.com/matsim-org/matsim-libs/tree/master/contribs/bicycle.
The extension is also described in this paper: https://doi.org/10.1016/j.procs.2017.05.424

# Resources package (src/main/java/resources)
Input data specific to the study area, including the network and travel diary, are specified in a properties file which is passed in as the first argument in runnable code. 
A template for this properties file is located in src/main/java/resources/example.properties. 
All paths given in this properties file are relative from the working directory which can be specified in each run configuration.

# Estimation package (src/main/java/estimation)
This package contains a tool for empirical mode choice estimation incorporating the street-level environment.
The estimation currently supports only multinomial logit models estimated using the BGFS algorithm.
This is based on the estimation tools in Smile (https://haifengl.github.io) and Apollo (http://www.apollochoicemodelling.com).

## RunMnl.java
Example model applying the SP and RP examples in Apollo (http://www.apollochoicemodelling.com/examples.html). 
 results should match the Apollo examples

## RunMnlManchester.java and RunMnlMelbourne.java
Script to run models for Manchester. The estimation requires two diary datasets.
First, the dataset containing fixed non-route-based predictors (e.g., sociodemographic attributes). 
Second, the dataset used for routing (see Routing package), includes origin and destination locations for each trip.
The fixed predictors dataset is passed in as an argument, while the routing dataset is specified in the _diary.file_ property of the properties file. 
The network must also be specified in the _matsim.road.network_ property.
Every record in the travel diary should correspond to a record in the routing dataset The correspondence is specified using a DiaryReader.IdMatcher object (see line 13) and passed in as an argument.

Dynamic updating of route estimates can be enabled or disabled using _ENABLE_DYNAMIC_ROUTING_ in dynamic/RouteDataDynamic.java (line 39).
If it is disabled, a fixed route is computed according to the starting values and used over all iterations of the estimation.

For a quick estimation, the estimation can be also run using pre-calculated initial route data from the previous run.
This can be enabled by setting _COMPUTE_ROUTE_DATA_ to false (line 11). This faster option is not possible with dynamic updating.