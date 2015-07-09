README
========

Week42
--------


Reflective Questions
--------------------


#### How would you implement a page which shows a list of files. See also List keys? <br>
- Use an other database. <br>

#### Why would you ever use a set-up with virtual machines in a real (production) environment? Or would you not?<br>

- Software inside of VM can not escape out to tamper with rest system, VM also helpful handling untrusted programms.<br>
- VM's operating system is stored on a virtual hard drive therefore no mess around with partitioning.<br>
- It is virtual machine so there is no spend of money for testing perpouse.<br>

#### Which of the optimizations made sense, which ones not? <br>

- Made sense:<br>
    Measuring impact - Checking the influence of changes.<br>
	Using protocol buffers instead of HTTP - very good while no need of human readable.<br>
	Caching contacts using memcached - will speed up the performance.<br>

- Not that much:<br>
	Using a faster server framework<br>
    

#### What should be improved in this exercise if it is given to students in the future?
    
- Everything seems ok. Efficiency level on different language, OS and techniques are not same for all student,therefore small homework or questionnaires in advance could be helpful.<br>