## ubuntu install git and config github
- install
```
sudo apt-get install git
```
- config
```
git config --global user.name 'lujiamin'
git config --global user.email 'xx@xx'
```
- create ssh
```
ssh-keygen -C 'xx@xx' -t rsa
```
- upload ssh key
  - copy the content in ~/.ssh/id_rsa.pub
- test
```
ssh -v git@github.com
```