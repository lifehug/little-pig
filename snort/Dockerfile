FROM armv7/armhf-ubuntu 
MAINTAINER etherfuse
COPY interfaces /etc/network/interfaces

# ===== install all dependencies =====
RUN apt-get update && apt-get install -y \ 
  build-essential \ 
  libpcap-dev \
  libpcre3-dev \
  libdumbnet-dev \
  bison \ 
  flex \
  zlib1g-dev \
  liblzma-dev \ 
  openssl \
  libssl-dev \
  mysql-server \
  libmysqlclient-dev \
  mysql-client \
  autoconf \
  libtool

# # ==== install DAQ =====
# RUN mkdir ~/snort_src
# WORKDIR ~/snort_src
# RUN wget https://www.snort.org/downloads/snort/daq-2.0.6.tar.gz
# RUN tar -xvzf daq-2.0.6.tar.gz
# WORKDIR ~/snort_src/daq-2.0.6
# RUN ./configure && make && make install

# # ==== install Snort ==== 
# WORKDIR ~/snort_src
# RUN wget https://snort.org/downloads/snort/snort-2.9.8.2.tar.gz
# RUN tar -xvzf snort-2.9.8.2.tar.gz
# WORKDIR ~/snort_src/snort-2.9.8.2
# RUN ./configure --enable-sourcefire && make && make install
# RUN ldconfig
# RUN ln -s /usr/local/bin/snort /usr/sbin/snort

# # Create the snort user and group:
# RUN groupadd snort
# RUN useradd snort -r -s /sbin/nologin -c SNORT_IDS -g snort
# # Create the Snort directories:
# RUN mkdir /etc/snort
# RUN mkdir /etc/snort/rules
# RUN mkdir /etc/snort/rules/iplists
# RUN mkdir /etc/snort/preproc_rules
# RUN mkdir /usr/local/lib/snort_dynamicrules
# RUN mkdir /etc/snort/so_rules
# # Create some files that stores rules and ip lists
# RUN touch /etc/snort/rules/iplists/black_list.rules
# RUN touch /etc/snort/rules/iplists/white_list.rules
# RUN touch /etc/snort/rules/local.rules
# RUN touch /etc/snort/sid-msg.map
# # Create our logging directories:
# RUN mkdir /var/log/snort
# RUN mkdir /var/log/snort/archived_logs
# # Adjust permissions:
# RUN chmod -R 5775 /etc/snort
# RUN chmod -R 5775 /var/log/snort
# RUN chmod -R 5775 /var/log/snort/archived_logs
# RUN chmod -R 5775 /etc/snort/so_rules
# RUN chmod -R 5775 /usr/local/lib/snort_dynamicrules

# # Change Ownership on folders:
# RUN chown -R snort:snort /etc/snort
# RUN chown -R snort:snort /var/log/snort
# RUN chown -R snort:snort /usr/local/lib/snort_dynamicrules

# WORKDIR ~/snort_src/snort-2.9.8.2/etc/
# RUN cp *.conf* /etc/snort
# RUN cp *.map /etc/snort
# RUN cp *.dtd /etc/snort
# WORKDIR ~/snort_src/snort-2.9.8.2/src/dynamic-preprocessors/build/usr/local/lib/snort_dynamicpreprocessor/
# RUN cp * /usr/local/lib/snort_dynamicpreprocessor/

# COPY local.rules /etc/snort/rules/
# COPY snort.conf /etc/snort/
# COPY sid-msg.map /etc/snort/
# RUN snort -T -c /etc/snort/snort.conf -i eth0

# # ==== Install Barnyard ====
# WORKDIR ~/snort_src
# RUN wget https://github.com/firnsy/barnyard2/archive/7254c24702392288fe6be948f88afb74040f6dc9.tar.gz -O barnyard2-2-1.14-336.tar.gz
# RUN tar zxvf barnyard2-2-1.14-336.tar.gz
# RUN mv barnyard2-7254c24702392288fe6be948f88afb74040f6dc9 barnyard2-2-1.14-336
# WORKDIR ~/snort_src/barnyard2-2-1.14-336
# RUN autoreconf -fvi -I ./m4
# RUN ./configure --with-mysql --with-mysql-libraries=/usr/lib/arm-linux-gnueabihf && make && make install
# WORKDIR ~/snort_src/barnyard2-2-1.14-336
# COPY barnyard2.conf /etc/snort/
# # the /var/log/barnyard2 folder is never used or referenced
# # but barnyard2 will error without it existing
# RUN mkdir /var/log/barnyard2
# RUN chown snort.snort /var/log/barnyard2
# RUN touch /var/log/snort/barnyard2.waldo
# RUN chown snort.snort /var/log/snort/barnyard2.waldo




