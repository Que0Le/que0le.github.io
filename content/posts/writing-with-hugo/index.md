---
title: "Writing with Hugo"
date: "2022-11-30T00:00:00+01:00"
description: "Syntax and note on working with Hugo"
---

### Click to expand
Source: https://gist.github.com/pierrejoubert73/902cc94d79424356a8d20be2b382e1ab
````md
<details>
  <summary>Click me</summary>
  
  ### Heading
  1. Foo
  2. Bar
     * Baz
     * Qux

  ### Some Javascript
  ```js
  function logSomething(something) {
    console.log('Something', something);
  }
  ```
</details>
````

### Latex

```tex
% The following
$$\int_{a}^{b} x^2 dx$$
% Is an integral
```
$$\int_{a}^{b} x^2 dx$$

### Image
Create a folder in `posts` dir with `index.md` file for content. Copy images into the folder. Ref with:

```md
[nature](nature.jpg)
```
or
```html
<div style="align: left; text-align:center;">
    <img src="nature.jpg" alt="This is a centered resized image" width="60%" />
    <span style="display:block;">This is a centered resized image</span>
</div>

<div style="align: left; text-align:center;">
    <img src="" alt="" width="60%" />
    <span style="display:block;"></span>
</div>
```

<div style="align: left; text-align:center;">
    <img src="nature.jpg" alt="This is a centered resized image" width="60%" />
    <span style="display:block;">This is a centered resized image</span>
</div>

### Mention Hugo shortcodes in markdown
```go-html-template
{{</* myshortcode */>}}<p>Hello <strong>World!</strong></p>{{</* /myshortcode */>}}
```
<div style="align: left; text-align:center;">
    <img src="mention_shortcode.PNG" width="90%" />
    <!-- <span style="display:block;">This is a centered resized image</span> -->
</div>