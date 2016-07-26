FROM armv7/armhf-ubuntu 
MAINTAINER etherfuse
COPY interfaces /etc/network/interfaces

ENV home /root/snort_src
ENV snort snort-2.9.8.2
ENV daq daq-2.0.6
ENV barnyard barnyard2-2-1.14-336

# ===== install all dependencies =====
RUN apt-get update && apt-get install -y \ 
  autoconf \
  bison \ 
  build-essential \ 
  flex \
  libdumbnet-dev \
  liblzma-dev \ 
  libmysqlclient-dev \
  libpcap-dev \
  libpcre3-dev \
  libssl-dev \
  libtool \
  mysql-client \
  openssl \
  supervisor \
  zlib1g-dev \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# ================================= install DAQ ================================= 
WORKDIR ${home}
ADD https://www.snort.org/downloads/snort/${daq}.tar.gz ${daq}.tar.gz
RUN tar -xvzf ${daq}.tar.gz 
RUN cd ${daq} && ./configure && make && make install

# ================================= install Snort =================================  
WORKDIR ${home}
ADD https://www.snort.org/downloads/archive/snort/${snort}.tar.gz ${snort}.tar.gz
RUN tar -xvzf ${snort}.tar.gz
RUN cd ${snort} && ./configure --enable-sourcefire && make && make install
RUN ldconfig
RUN ln -s /usr/local/bin/snort /usr/sbin/snort

# Create the snort user and group:
RUN groupadd snort && useradd snort -r -s /sbin/nologin -c SNORT_IDS -g snort

# Create the Snort directories:
RUN mkdir /etc/snort && mkdir /etc/snort/rules && mkdir /etc/snort/rules/iplists && mkdir /etc/snort/preproc_rules && mkdir /usr/local/lib/snort_dynamicrules && mkdir /etc/snort/so_rules

# Create some files that stores rules and ip lists
RUN touch /etc/snort/rules/iplists/black_list.rules && touch /etc/snort/rules/iplists/white_list.rules

COPY local.rules /etc/snort/rules/
COPY sid-msg.map /etc/snort/
# Create our logging directories:
RUN mkdir /var/log/snort && mkdir /var/log/snort/archived_logs

# Adjust permissions:
RUN chmod -R 5775 /etc/snort && chmod -R 5775 /var/log/snort && chmod -R 5775 /var/log/snort/archived_logs && chmod -R 5775 /etc/snort/so_rules && chmod -R 5775 /usr/local/lib/snort_dynamicrules

# Change Ownership on folders:
RUN chown -R snort:snort /etc/snort && chown -R snort:snort /var/log/snort && chown -R snort:snort /usr/local/lib/snort_dynamicrules

WORKDIR ${home}/${snort}/etc/
RUN cp *.conf* /etc/snort && cp *.map /etc/snort && cp *.dtd /etc/snort

WORKDIR ${home}/${snort}/src/dynamic-preprocessors/build/usr/local/lib/snort_dynamicpreprocessor/
RUN cp * /usr/local/lib/snort_dynamicpreprocessor/
COPY snort.conf /etc/snort/

# =================================  Install Barnyard ================================= 
WORKDIR ${home}
ADD https://github.com/firnsy/barnyard2/archive/7254c24702392288fe6be948f88afb74040f6dc9.tar.gz ${barnyard}.tar.gz
RUN tar zxvf ${barnyard}.tar.gz
RUN mv barnyard2-7254c24702392288fe6be948f88afb74040f6dc9 ${barnyard}
WORKDIR ${barnyard}
RUN autoreconf -fvi -I ./m4
RUN ln -s /usr/include/dumbnet.h /usr/include/dnet.h
RUN ldconfig
RUN ./configure --with-mysql --with-mysql-libraries=/usr/lib/arm-linux-gnueabihf && make && make install
COPY barnyard2.conf /etc/snort/

# the /var/log/barnyard2 folder is never used or referenced
# but barnyard2 will error without it existing
RUN mkdir /var/log/barnyard2 && chown snort.snort /var/log/barnyard2 && touch /var/log/snort/barnyard2.waldo && chown snort.snort /var/log/snort/barnyard2.waldo

WORKDIR ${home}
RUN mkdir -p /var/log/supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf
CMD ["/usr/bin/supervisord"]


