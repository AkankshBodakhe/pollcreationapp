const express = require("express");
const bcrypt = require("bcryptjs");
const passport = require("passport");
const User = require("../models/User");

const router = express.Router();

// Register Route
router.post("/register", async (req, res) => {
  console.log(req.body)
  const { fname, lname, email, password, phoneNumber } = req.body;

  try {
    let user = await User.findOne({ where: { email } });
    console.log(user);
    if (user) {
      return res.status(400).json({ msg: "User already exists" });
    }

    const hashedPassword = await bcrypt.hash(password, 10);

    user = await User.create({ fname, lname, email, password: hashedPassword, phoneNumber });
    console.log("User registered successfully")
    res.json({ msg: "User registered successfully" });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: error.message });
  }
});

// Login Route
router.post("/login", (req, res, next) => {
  console.log(req.isAuthenticated()); //  Debug request data

  passport.authenticate("local", (err, user, info) => {
    if (err) {
      console.error(" Authentication error:", err);  //  Log error
      return res.status(500).json({ msg: "Server error", error: err });
    }

    if (!user) {
      console.warn(" Authentication failed:", info.message);  // Log reason
      return res.status(400).json({ msg: info.message });
    }

    req.logIn(user, (err) => {
      if (err) {
        console.error(" Login session error:", err);  // Log session error
        return res.status(500).json({ msg: "Session error", error: err });
      }

      console.log("User logged in:", user.email);
      return res.json({ msg: "Logged in successfully", user });
    });
  })(req, res, next);  //  Ensure authentication middleware is called correctly
});


// Logout Route
router.get("/logout", (req, res) => {
  req.logout(() => {
    console.log("logout");
    res.json({ msg: "Logged out successfully" });
  });
});

module.exports = router;
