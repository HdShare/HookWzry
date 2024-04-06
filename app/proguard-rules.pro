
-keep class me.hd.hookwzry.hook.HookEntry

#使用给定文件中的关键字作为要混淆方法能和字段的名称,默认使用像'a'这样的短名称
-obfuscationdictionary proguard-dict.txt
#指定包含合法的用于混淆后的类的名称的字符集合的文本文件
-classobfuscationdictionary proguard-dict.txt
#通过文本文件指定合法的包名
-packageobfuscationdictionary proguard-dict.txt

#处理扩展类和类成员的访问修饰符
-allowaccessmodification
#允许多个字段和方法相同名称,只是参数和返回类型不同
-overloadaggressively
#重新包装所有已重命名的类文件,移动到给定的单一包中
-repackageclasses 'android.core'
