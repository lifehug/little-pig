# Little Pig

A network monitoring tool that will sniff ip traffic over a network interface. Using a containerized snort this will work on both Arm and Intel architectures. Run on a raspberry pi or any computer and sniff all traffic over interface eth0. All ip traffic will be stored in a queryable format in a MySQL database, which can be reached from any container attached to the `database` network. 

Hook interface eth0 on your raspberry pi to a tap put this little pig to work. 


#### Run on an intel architecture

```
docker-compose -f intel.yml up
```

#### Run on a raspberry pi or other arm architecture

```
docker-compose -f arm.yml up
```