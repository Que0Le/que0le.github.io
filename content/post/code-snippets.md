+++
title = "Code snippets and notes"
date = "30/11/2022"
author = "me"
description = "Collected snippets over time ..."
+++

---
## Fix Thinkpad T14 touchpad stops working
My machine has Ubuntu 20.04 OEM installed, kernel 5.14, X11. After trying for a while, this commands seems to install the correct hardware:
```bash
sudo apt install linux-generic-hwe-20.04
```
---
## Setup working environment
Git
```
# Permission too open for private key
chmod 700 ~/.ssh/key_name
```
---
## Python
```python
# Hold execution terminal after execution
python3 -i script.py

# create 2d array with values in range
test_inp = np.arange(1, 31).reshape(5,6)

#
```

---
## Golang
Tell staticchecker to ignore the unused function
```go
//lint:ignore U1000 Ignore unused function temporarily for debugging
```
---

## Developing Linux kernel modules

#### Find filename with partial path:
```bash
find / -path \*asm/mmiowb.h
# Remove permission denied messages and filter 5.8 kernel
find / -path \*linux/openat2.h 2>/dev/null | grep 5.8
```


#### Include these paths. "**" might mean recursive 
```bash
${workspaceFolder}/**
/usr/include/
/usr/local/include/
/usr/src/linux-hwe-5.8-headers-5.8.0-63/include/uapi/
/usr/src/linux-headers-5.8.0-63-generic/**
```

#### vscode exclude some files:
```json
"files.exclude": {
  "**/*.cmd": true,
  "**/*.ko": true,
  "**/*.mod": true,
  "**/*.mod.c": true,
  "**/*.mod.o": true,
  "**/*.o": true
}
```

#### Kernel print format: https://www.kernel.org/doc/Documentation/printk-formats.txt

- No support for float.
- Integer types
- If variable is of Type,		use printk format specifier:

	
		int			%d or %x
		unsigned int		%u or %x
		long			%ld or %lx
		unsigned long		%lu or %lx
		long long		%lld or %llx
		unsigned long long	%llu or %llx
		size_t			%zu or %zx
		ssize_t			%zd or %zx
		s32			%d or %x
		u32			%u or %x
		s64			%lld or %llx
		u64			%llu or %llx

