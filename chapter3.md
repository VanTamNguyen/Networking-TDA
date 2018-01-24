# Chapter 3: Transport Layer

### 3.1 Transport Layer and Network Layer
* Network layer provides **logical communication** between *hosts (end systems)*
* Transport layer provides **logical communication** between *processes* running on different hosts
* Transport layer is implemented only in end systems (view this [image](images/layers.png) for more visualization)
* Transport layer uses the service provided by the Network layer to provide its service
* Example
    * There 2 houses, one in East Coast and one in West Coast. In each house, there are 12 children living in. They are cousins. They love to write to each other in other house every week.
    * Ann lives in the house in East Coast and Bill lives in the house in West Coast. They are responsible for mail collection and distribution. Every weekend, Ann and Bill will collect mails from cousins in the house to put to mailbox and get the arrived mails from mailbox to distribute to their cousins.
    * Every weekend, the postmans will come with arrived mails to put to mailbox and also get the waiting mails from mailbox to send to destination house.
    * In this example, houses are the hosts (end systems); children are the processes running on hosts; postmans are the network layer, they provide a logical communication between houses; Ann and Bill are transport layer, they provides a logical communication between children (processes).
    * We can see that in this example, Ann and Bill only all their work within the house they living, they are not involved in moving mails from one mail center to another. This is similar to transport layer, only implemented in end systems.
    * Ann and Bill use the service provided by the postmans to do their job.

### 3.2 Services Provided by Transport Layer
* **Multiplex and Demultiplex**
    * **Demultiplex** is the job of delivering incoming transport layer segments to the correct socket in receiving host based on the port; demultiplex happens at destination host.
    * **Multiplex** is the job of gathering data chunks at source host from different socket, encapsulating each data chunk with header information (that will be used later by demultiplex) and creating segments to pass to network layer; multiplex happens at source host.
* **Reliable Data Transfer**
    * Transport layer may or may not provide *reliable data transfer* service. For example, in the Internet, TCP provides *reliable data transfer* servive but UDP does not. *Reliable data transfer* means data will be transfered in order and integrity.
* **Error Detection**
* **Congestion Control**


### 3.3 Reliable Data Transfer Protocol
#### 3.3.1 Stop and Wait
#### 3.3.2 Pipeline
* **Go Back N**
* **Selective Repeat**

### 3.4 UDP
* **UDP Segment**
* **UDP Checksum**

### 3.5 TCP
#### 3.5.1 Three Way Handshake
#### 3.5.2 TCP Segment Structure
#### 3.5.3 Sequence Number and Acknowledgement Number 
#### 3.5.4 Reliable Data Transfer of TCP
#### 3.5.5 TCP Connection Management
#### 3.5.5 Flow Control
#### 3.5.6 Congestion Control

 
