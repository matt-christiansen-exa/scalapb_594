package com.test

import com.test.pb.testmsg.TestMsg

object Main {
  def main(args: Array[String]) {
    val msg = TestMsg("Hello")
    println(msg.msg)
  }
}
