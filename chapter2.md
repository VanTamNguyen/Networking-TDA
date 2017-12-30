# Chapter 2: Application Layer

### 2.1 Principles of Network Applications
At the core of network application development is writing programs that run on different end systems and communicate with each other over the network.For example, Web application contains browser running on user's hosts and Web server running on the server. Browser and Web server communicate with each other over the Internet.<br/>

Importantly, we don't need to write programs running on network core devices such as routers and link-layer switches.

* **Network Application Architecture** -- Keep in mind that application architecture is distinctly different from the network architecture (layered architecture). There are 2 main architectures in network application: client-server and peer-to-peer.
    * The client-server architecture has one host called *server* that is always-on and services requests from many *clients*. Note that, in the client-server architecture, the clients don't communicate directly to each other, they do via the server. Another characteristic of client-server architecture is that the server is fixed, well-known address, called an IP address.
    * The peer-to-peer architecture has minimal (or no) reliance on dedicated server. Instead the application expoits the direct communication between pair of connected hosts, called *peers*. 

* **The Process Communicating**
    * A process can be thought of as a program running within an end system. When processes running on the same end system they can communicate with each other with interprocess communication. Processes on different end systems communicate with each other by exchange the messages across the computer network.
    * *In the context of communication session between a pair of processes, the process that initiates the communication is called client. The process that waits to be connected is called the server.*
    * A process sends messages into, and receives messages from, the network through a software interface called **socket**. A socket is the interface between the application layer and the transport layer within a host. Since the socket is the programming interface with which applications are built, the application developer has control of everything on application layer side of the socket and has little control of transport layer side of the socket. The only control the application developer has on transport layer side is (1) the transport protocol and (2) few transport parameter such as muximum buffer.
    * In the Internet, the host is idenitfied by its **IP address**. In addition to knowing the address of the destination host, the sending process must also identify the process running on the destination host. This information is needed because a host can run many network application processes. A destination **port number** serves this purpose. For example, Web server is identified by port 80.

* **Transport Services Available to Applications**

### 2.2 DNS: The Internet's Directory Service

### 2.3 Socket Programming