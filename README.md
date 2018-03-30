# RxSlideUp: Reactive listeners for [SlideUp library][1]

 [ ![Download](https://api.bintray.com/packages/ztrap-llc/maven/RxSlideUp/images/download.svg) ](https://bintray.com/ztrap-llc/maven/RxSlideUp/_latestVersion)

## Install

gradle

```groovy
implementation 'ru.ztrap:RxSlideUp:1.0.1'
```
maven
```xml
<dependency>
  <groupId>ru.ztrap</groupId>
  <artifactId>RxSlideUp</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Usage

```java
View slideView = findViewById(R.id.slideView);
SlideUp slideUp = new SlideUp.Builder(slideView)
                .withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)
                .build();

//listening SlideUp.Listener.Events
RxSlideUp.events(slideUp).subscribe();

//listening SlideUp.Listener.Slide
RxSlideUp.slide(slideUp).subscribe();

//listening SlideUp.Listener.Visibility
RxSlideUp.visibility(slideUp).subscribe();
```

That's all! Enjoy reactive programming with [RxJava][2], [SlideUp][1] and RxSlideUp!

## License

       Copyright 2018 zTrap

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

  [1]: https://github.com/mancj/SlideUp-Android
  [2]: https://github.com/ReactiveX/RxJava/tree/1.x
