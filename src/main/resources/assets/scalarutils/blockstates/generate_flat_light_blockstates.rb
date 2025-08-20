require 'json'

colors = %w[
  white light_gray gray black brown red orange yellow lime green
  cyan light_blue blue purple magenta pink
]

colors.each do |color|
  # Normal variant
  normal_blockstate = {
    "variants" => {
      "lit=false" => { "model" => "scalarutils:block/#{color}_flat_light" },
      "lit=true"  => { "model" => "scalarutils:block/#{color}_flat_light_on" }
    }
  }
  File.write("#{color}_flat_light.json", JSON.pretty_generate(normal_blockstate))

  # Inverted variant (on/off swapped)
  inverted_blockstate = {
    "variants" => {
      "lit=false" => { "model" => "scalarutils:block/#{color}_flat_light_on" },
      "lit=true"  => { "model" => "scalarutils:block/#{color}_flat_light" }
    }
  }
  File.write("inverted_#{color}_flat_light.json", JSON.pretty_generate(inverted_blockstate))
end