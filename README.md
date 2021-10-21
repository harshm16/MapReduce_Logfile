# Harsh Mishra - UIN 653554247

# Homework 2 (CS 441)

### Problem Statement: Create a program for parallel distributed processing of the Logfile in order to generate pre-defined statistics about the respective log files. The statistics are mentioned in more detail below.

#### This Git repo contains the usage of map/reduce model on a randomly generated log file. This baseline of this repo was taken from: [git@github.com:0x1DOCD00D/LogFileGenerator.git](https://github.com/google-research/torchsde) , where you can find the code for generation of the Log file. 
#### Some files that were present in the above-mentioned repo have been removed as they were causing some dependency issues while creating a jar. Please refer the above link in case you want to know more about the process of the generation of the log files.

### Task 1:
#### Compute a CSV file that shows the distribution of different types of messages across predefined time intervals and injected string instances of the designated regex pattern for these log message types.

### Approach:
#### Convert the Timestamp column to epoch time and use the user-defined "bin" to distribute them into intervals. Mapper (Class - Map_part1) then uses those bins to create the following key: "'bin','error_type,'matching_string'", and use its frequency, i.e., 1 as the value. The reducer (Class - Reduce_part1) then just sums up all the values if it finds the same keys.

#### To run -:
``` 
hadoop jar jar_name.jar Main input_dir output_dir 1
``` 

### Task 2: 
#### Compute time intervals sorted in the descending order that contained most log messages of the type ERROR with injected regex pattern string instances.

### Approach:
#### Use the map-reduce job used in task 1 and then chain it with the following Map-reduce jobs. Mapper (Class - Map_part2_2) check if error type in the respective line is of the type "ERROR", if it is, it makes ('bin','frequency(1)') as the key-value pair. The reducer (Class - Reduce_part1) used is the same as the Task1. The output from the reducer is sent to another Mapper (Class - Map_part2_3) which swaps the key-value pair around, i.e., ('Frequency','Bin'). The reducer (Class - Reduce_part2) creates a comma separated string of all the values with the same key. Finally, the comparator (Class - Custom Comparator) sorts the keys of the key value pair in the descending order.

#### To run -:
``` 
hadoop jar jar_name.jar Main input_dir output_dir_1 output_dir_2 output_dir_3 2
``` 

### Task 3:
#### For each message type produce the number of the generated log messages.

### Approach :
#### Mapper (Class - Map_part3) creates the following key-value pair, ('error_type','frequency(1)'). The reducer (Class - Reduce_part3) sums up all the values with the same key, thus finding the number of generated log messages of the same log type.


#### To run -:
``` 
hadoop jar jar_name.jar Main input_dir output_dir 3
``` 

### Task 4:
#### Produce the number of characters in each log message for each log message type that contain the highest number of characters in the detected instances of the designated regex pattern.

### Approach :
#### Mapper (Class - Map_part4) creates ("'error_type':'matches string's length'","frequency(1)") as the key-value pair. The reducer (Reduce_part4) find all the values with the same key and just puts 1 as the value. The next mapper (Map_part4_2) splits the key with the ":" and creates ('error_type','matched_string_length') as the key-value pair. The reducer (Reduce_part4_2) finds the highest value out of the values for a particular list and writes that into the output file.


#### To run -:
``` 
hadoop jar jar_name.jar Main input_dir output_dir_1 output_dir_2 4
``` 

### How to deploy it on AWS:
#### Follow [this youtube video](https://youtu.be/isdRPZYHVts) where I have shown all the steps involved.

### Some observations / recommendations:
#### i) In case you are running the map reducer job on a Hadoop Sandbox VM, the Jar file should be present in your VM and not in the Hadoop File system. The log file and the input directory need to be present in the Hadoop File system.
#### ii) The output directory should not be present beforehand, it gets created once the jobs get executed. 
#### iii) The Main classes that run each task have a block of code commented out, which can be used to delete the output files in case they already exist. 
#### iv) While running on a Hadoop Sandbox VM only 1 reducer was getting spawned thus there was only 1 output file getting created irrespective of the number or size of the log file. Whereas on AWS the output files were more in number as AWS' EMR instance (Default - 1 Master m5.xlarge, 2 Workers m5.xlarge) creates multiple reducers.
#### v) The bin to divide the log file into intervals can be configured by modifying it in the User_Split.conf file. The current implementation is, epoch_time / bin_size % bin_size. Although this means that the logs aren't exactly binned according to their time interval, it does help keeping down the interval to a certain number (bin_size) irrespective of the number of log messages present in the log file.

## Thank you
